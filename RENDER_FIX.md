# Fix: "Couldn't find a package.json file in /opt/render/project/src"

## Problem
Render is looking for `package.json` in the wrong directory (`/opt/render/project/src`), but your `package.json` is located in the `client/` subdirectory.

## Solution

### Option 1: Using render.yaml (Recommended)
The `render.yaml` file in the root directory now includes the correct `rootDir` setting:

```yaml
- type: web
  name: zidioconnect-frontend
  env: node
  rootDir: client  # ← This tells Render to look in the client/ directory
  buildCommand: npm install && npm run build
  startCommand: npx vite preview --host 0.0.0.0 --port $PORT
```

### Option 2: Manual Configuration in Render Dashboard
1. Go to your Render Dashboard
2. Select your frontend service
3. Go to **Settings**
4. Find **Root Directory** field
5. Set it to: `client`
6. Save changes

## Key Points
- ✅ **Root Directory must be set to `client`** for the frontend service
- ✅ **Root Directory must be set to `backend`** for the backend service
- ✅ The `render.yaml` file has been created with correct configuration
- ✅ Both services are configured with proper root directories

## Deployment Steps

1. **Commit the render.yaml file:**
   ```bash
   git add render.yaml
   git commit -m "Add Render configuration with correct rootDir"
   git push origin main
   ```

2. **Deploy via Render Blueprint:**
   - Go to Render Dashboard
   - Click "New" → "Blueprint"
   - Connect your GitHub repository
   - Render will automatically use the `render.yaml` configuration

3. **Or deploy manually:**
   - Create services in Render Dashboard
   - Make sure to set **Root Directory** to `client` for frontend
   - Make sure to set **Root Directory** to `backend` for backend

## Verification
After deployment, verify:
- ✅ Frontend service builds successfully
- ✅ Backend service builds successfully
- ✅ No "package.json not found" errors
- ✅ Services are running and accessible

## Files Changed
- ✅ `render.yaml` - Added with correct rootDir configuration
- ✅ `RENDER_DEPLOYMENT_GUIDE.md` - Complete deployment guide
- ✅ `RENDER_FIX.md` - This file with fix instructions

